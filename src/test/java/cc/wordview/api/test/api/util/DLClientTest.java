/*
 * Copyright (c) 2024 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.test.api.util;

import cc.wordview.api.service.util.DLClient;
import cc.wordview.api.util.FileHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DLClientTest {
    @Test
    void downloadSubtitle() throws Exception {
        assertDoesNotThrow(() -> {
            DLClient.downloadSubtitle("gqiRJn7me-s", "ja");
            String subtitle = FileHelper.read("/tmp/gqiRJn7me-s.ja.vtt");
            assertEquals(actualSubtitle, subtitle);
        });
    }

    @Test
    void downloadVideo() throws Exception {
        assertDoesNotThrow(() -> {
            DLClient.downloadVideo("gqiRJn7me-s");
            Path file = Paths.get( "/tmp/gqiRJn7me-s.mp3");
            assertTrue(Files.exists(file));
        });
    }

    private static final String actualSubtitle = "WEBVTT\n" +
            "Kind: captions\n" +
            "Language: ja\n" +
            "\n" +
            "00:00:03.500 --> 00:00:12.980\n" +
            " \n" +
            "\n" +
            "00:00:14.080 --> 00:00:18.900\n" +
            "消えそうな星を眺めて\n" +
            "\n" +
            "00:00:19.160 --> 00:00:24.860\n" +
            "憂いを夜空に吐き出す\n" +
            "\n" +
            "00:00:25.420 --> 00:00:30.200\n" +
            "落ちる一筋の願いは\n" +
            "\n" +
            "00:00:30.420 --> 00:00:35.780\n" +
            "淡く溶けて消えた\n" +
            "\n" +
            "00:00:36.580 --> 00:00:41.840\n" +
            "君のいつもの口癖も\n" +
            "\n" +
            "00:00:42.080 --> 00:00:47.520\n" +
            "負けず嫌いな性格も\n" +
            "\n" +
            "00:00:47.700 --> 00:00:52.860\n" +
            "隣で笑うその顔も\n" +
            "\n" +
            "00:00:53.560 --> 00:00:58.280\n" +
            "大事にしてたのに\n" +
            "\n" +
            "00:00:59.440 --> 00:01:04.640\n" +
            "気付けば何故か言えなくなった\n" +
            "\n" +
            "00:01:04.860 --> 00:01:10.740\n" +
            "君に向けた「好き」という言葉\n" +
            "\n" +
            "00:01:11.140 --> 00:01:16.380\n" +
            "心の何処かでは諦めてたのかな\n" +
            "\n" +
            "00:01:16.480 --> 00:01:22.000\n" +
            "戻れないと知ってても\n" +
            "\n" +
            "00:01:22.248 --> 00:01:27.628\n" +
            "二人の過去を全て壊して\n" +
            "\n" +
            "00:01:28.148 --> 00:01:33.228\n" +
            "もう僕なんか嫌いになってよ\n" +
            "\n" +
            "00:01:33.528 --> 00:01:39.148\n" +
            "そしたら君の事を今すぐにでも\n" +
            "\n" +
            "00:01:39.352 --> 00:01:44.732\n" +
            "忘れてしまえるのに\n" +
            "\n" +
            "00:01:56.768 --> 00:02:01.828\n" +
            "僕の最高の思い出を\n" +
            "\n" +
            "00:02:02.120 --> 00:02:07.420\n" +
            "胸の中にまた隠して\n" +
            "\n" +
            "00:02:08.300 --> 00:02:13.460\n" +
            "先の見えないこの道を\n" +
            "\n" +
            "00:02:13.696 --> 00:02:18.016\n" +
            "今日も一人歩く\n" +
            "\n" +
            "00:02:19.556 --> 00:02:24.516\n" +
            "いつしか何も信じられない\n" +
            "\n" +
            "00:02:25.100 --> 00:02:30.620\n" +
            "そんな自分がこの場所にいた\n" +
            "\n" +
            "00:02:30.728 --> 00:02:36.188\n" +
            "離れたくないけど僕はもう行かなきゃ\n" +
            "\n" +
            "00:02:36.360 --> 00:02:41.960\n" +
            "帰れないと知ってても\n" +
            "\n" +
            "00:02:42.240 --> 00:02:47.660\n" +
            "愛した日々もその温もりも\n" +
            "\n" +
            "00:02:47.708 --> 00:02:53.328\n" +
            "抱きしめ合って過ごした夜も\n" +
            "\n" +
            "00:02:53.524 --> 00:02:59.304\n" +
            "嘘じゃないと自分に言い聞かせて\n" +
            "\n" +
            "00:02:59.484 --> 00:03:04.664\n" +
            "逃げていたいだけなんだよ\n" +
            "\n" +
            "00:03:07.904 --> 00:03:13.464\n" +
            "二人の過去を全て壊して\n" +
            "\n" +
            "00:03:13.584 --> 00:03:19.324\n" +
            "もう僕なんか嫌いになってよ\n" +
            "\n" +
            "00:03:19.504 --> 00:03:24.804\n" +
            "そしたら君の事を今すぐにでも\n" +
            "\n" +
            "00:03:24.964 --> 00:03:30.644\n" +
            "忘れてしまえるのに\n" +
            "\n" +
            "00:03:54.136 --> 00:03:58.756\n" +
            "君に最後の口づけを\n" +
            "\n" +
            "00:03:59.280 --> 00:04:04.580\n" +
            "今まで本当にありがとう\n" +
            "\n" +
            "00:04:05.460 --> 00:04:10.160\n" +
            "重なる二人の想いは\n" +
            "\n" +
            "00:04:10.580 --> 00:04:15.400\n" +
            "涙の味がした\n" +
            "\n";
}
