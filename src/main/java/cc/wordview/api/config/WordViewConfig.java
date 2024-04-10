package cc.wordview.api.config;

import org.springframework.stereotype.Component;

@Component
public class WordViewConfig {
        private boolean production;

        public boolean isProduction() {
                return production;
        }

        public void setProduction(boolean production) {
                this.production = production;
        }
}
