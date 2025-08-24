#  Copyright (c) 2025 Arthur Araujo
#
#  This program is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program. If not, see <https://www.gnu.org/licenses/>.

import os
from pathlib import Path
from posixpath import dirname
import sys
from colorama import Fore, Style, init
from sys import platform
import shutil

# Initialize colorama
init(autoreset=True)

EXTERNAL_FOLDER_PATH = Path("/etc/wordview-resources")


def main():
    if platform == "win32":
        print_error("This script is intended for unix-like systems")
        return
    
    if not 'SUDO_UID' in os.environ:
        print_error("You need to have root privileges to run this script.\nPlease try again, this time using 'sudo'. ")
        sys.exit(1)

    EXTERNAL_FOLDER_PATH.mkdir(parents=True, exist_ok=True)
    
    print("Enter the name of the user that will own the folder: ")
    name = input()

    shutil.chown(EXTERNAL_FOLDER_PATH, user=name, group=name)

    classpath = dirname(os.path.abspath(__file__))

    copy_all_folders(classpath, EXTERNAL_FOLDER_PATH, name)
    print_success(f"Done! You may now update the .properties files with the new directory: {EXTERNAL_FOLDER_PATH}")


def update_classpath_in_properties(file_path, new_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        lines = file.readlines()

    updated_lines = []
    for line in lines:
        if '=' in line and 'classpath:/' in line:
            key, value = line.split('=', 1)
            value = value.replace('classpath:/', new_path)
            updated_lines.append(f'{key}={value}')
        else:
            updated_lines.append(line)

    with open(file_path, 'w', encoding='utf-8') as file:
        file.writelines(updated_lines)



def copy_all_folders(src_dir, dest_dir, user):
    print_info(f"Copying classpath contents")

    if not os.path.exists(src_dir):
        print_error(f"Source folder '{src_dir}' does not exist.")
        sys.exit(1)

    for item in os.listdir(src_dir):
        s = os.path.join(src_dir, item)
        d = os.path.join(dest_dir, item)

        if os.path.isdir(s):
            shutil.copytree(s, d, dirs_exist_ok=True)

    for root, dirs, files in os.walk(dest_dir):
        for momo in dirs:
            shutil.chown(os.path.join(root, momo), user=user, group=user)
        for momo in files:
            shutil.chown(os.path.join(root, momo), user=user, group=user)

    shutil.chown(dest_dir, user=user, group=user)



def print_info(message): print(f"{Fore.CYAN}▶ {message}{Style.RESET_ALL}")
def print_success(message): print(f"{Fore.GREEN}  ✔ {message}")
def print_error(message): print(f"{Fore.RED}  ✘ {message}")
def print_warning(message): print(f"{Fore.YELLOW}  ⚠ {message}")


if __name__ == "__main__":
    main()