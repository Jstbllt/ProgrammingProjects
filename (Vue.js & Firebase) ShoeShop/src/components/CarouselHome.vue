<template>
  <Carousel :autoplay="3500" :wrap-around="true">
    <Slide v-for="image in images" :key="image.id">
      <base-button :direction="image.direction" :params="{ id: image.param }">
        <div :class="image.class">
          <p>{{ image.text }}</p>
          <div>
            <img :src="image.url" />
          </div>
        </div>
      </base-button>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template>

<script>
import { Carousel, Pagination, Slide, Navigation } from "vue3-carousel";
import BaseButton from "@/components/BaseButton.vue";

import "vue3-carousel/dist/carousel.css";

export default {
  name: "CarouselHome",
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
    BaseButton,
  },
  data() {
    return {
      images: [
        {
          id: 1,
          url: require("@/assets/chaussure-demo.png"),
          class: "discover box",
          text: "Discover the new shoes models in 3D",
          direction: "ShoePage",
          param: 4,
        },
        {
          id: 2,
          url: require("@/assets/discount.png"),
          class: "discount box",
          text: "Get a 10% discount on your first order with the code WELCOME10",
        },
        {
          id: 3,
          url: require("@/assets/presto.png"),
          class: "exclusive box",
          text: "Discover rare limited edition sneakers ",
          direction: "ShoePage",
          param: 8,
        },
      ],
    };
  },
};
</script>
<style scoped>
.box {
  margin: 2% 6% 2% 6%;
  padding: 2%;
  font-size: 280%;
  font-weight: bold;
  text-align: left;
  display: flex;
  flex-direction: row;
  color: white;
}
.discover {
  background: linear-gradient(to bottom right, rgb(100, 117, 140), white);
}

.discount {
  background: linear-gradient(to top right, rgb(100, 117, 140), white);
}

.exclusive {
  background: linear-gradient(to bottom left, rgb(100, 117, 140), white);
}

img {
  max-width: 100%;
}

@media screen and (max-width: 1078px) {
  img {
    display: none;
  }
}
</style>
