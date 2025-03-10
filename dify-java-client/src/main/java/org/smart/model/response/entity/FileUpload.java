package org.smart.model.response.entity;


import java.util.List;

/**
 * 文件上传配置
 */
public class FileUpload {
    private Image image;

    // Getters and setters

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 图片设置
     */
    public static class Image {
        private boolean enabled;
        private int numberLimits;
        private List<String> transferMethods;

        // Getters and setters

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getNumberLimits() {
            return numberLimits;
        }

        public void setNumberLimits(int numberLimits) {
            this.numberLimits = numberLimits;
        }

        public List<String> getTransferMethods() {
            return transferMethods;
        }

        public void setTransferMethods(List<String> transferMethods) {
            this.transferMethods = transferMethods;
        }
    }
}