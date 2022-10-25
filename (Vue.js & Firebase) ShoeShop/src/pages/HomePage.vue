<template>
  <carousel-home />
  <main>
    <section class="list">
      <shoe-box
        v-for="shoe in shoes"
        v-bind:key="shoe.title"
        class="box"
        :shoe="shoe"
      />
    </section>
  </main>
</template>

<script>
import ShoeBox from "@/components/ShoeBox.vue";
import CarouselHome from "@/components/CarouselHome.vue";

export default {
  components: { ShoeBox, CarouselHome },
  name: "HomePage",
  computed: {
    shoes() {
      return this.$store.getters.getShoes;
    },
  },
};
</script>

<style scoped>
.en-tete {
  margin: 2% 6% 2% 6%;
  background: linear-gradient(to bottom right, rgb(100, 117, 140), white);
  padding: 2%;
  font-size: 280%;
  font-weight: bold;
  text-align: left;
  display: flex;
  flex-direction: row;
  align-content: space-between;
  color: white;
}

.en-tete img {
  max-width: 100%;
}
@media screen and (max-width: 1078px) {
  .en-tete img {
    display: none;
  }
}
.en-tete p {
  margin-top: 0;
  margin-bottom: 0;
  padding: 2%;
}
.box {
  border: solid gray 1px;
  margin-bottom: 10%;
  width: 350px;
  margin: 15px;
  padding: 15px;
  display: flex;
  flex-direction: column;
}
.list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>
