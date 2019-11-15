package com.example.mycatapp;

public class CatImages {

        private String width;
        private String id;
        private String url;
        private CatBreed[] breeds;
        private String height;
        public String getWidth ()
        {
            return width;
        }
        public void setWidth (String width)
        {
            this.width = width;
        }
        public String getId ()
        {
            return id;
        }
        public void setId (String id)
        {
            this.id = id;
        }
        public String getUrl ()
        {
            return url;
        }
        public void setUrl (String url)
        {
            this.url = url;
        }
        public CatBreed[] getBreeds ()
        {
            return breeds;
        }
        public void setBreeds (CatBreed[] breeds)
        {
            this.breeds = breeds;
        }
        public String getHeight ()
        {
            return height;
        }
        public void setHeight (String height)
        {
            this.height = height;
        }
        @Override
        public String toString()
        {
            return "ClassPojo [width = "+width+", id = "+id+", url = "+url+", breeds = "+breeds+", height = "+height+"]";
        }
    }

