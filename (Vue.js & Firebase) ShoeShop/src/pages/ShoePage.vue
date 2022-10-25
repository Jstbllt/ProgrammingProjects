<template>
  <main>
    <shoe-box-3d :shoe="shoe" :color="color_shoe" />
    <div class="infos">
      <h3>{{ shoe.title }}</h3>
      <p>{{ shoe.description }}</p>
      <p>{{ shoe.price }} $</p>
      <div>
        <span
          v-for="i in 5"
          :key="i"
          :class="{ shoe__star: true, 'shoe__star--dark': i > shoe.grade }"
          @click="updateGrade(i)"
          ><font-awesome-icon icon="fa-solid fa-star"
        /></span>
      </div>
      <div class="colors">
        <base-button
          class="white"
          @click="updateColorShoe('White')"
        ></base-button>
        <base-button
          class="blue"
          @click="updateColorShoe('Blue')"
        ></base-button>
        <base-button class="red" @click="updateColorShoe('Red')"></base-button>
        <base-button
          class="green"
          @click="updateColorShoe('Green')"
        ></base-button>
        <base-button
          class="purple"
          @click="updateColorShoe('Purple')"
        ></base-button>
      </div>

      <select v-model="article.size">
        <option disabled value="">Please choose a size</option>
        <option>US 4 &nbsp;&nbsp; - &nbsp; EU 36</option>
        <option>US 4.5 - &nbsp; EU 36 2/3</option>
        <option>US 5 &nbsp;&nbsp; - &nbsp; EU 37 1/3</option>
        <option>US 5.5 - &nbsp; EU 38</option>
        <option>US 6 &nbsp;&nbsp; - &nbsp; EU 38 2/3</option>
        <option>US 6.5 - &nbsp; EU 39 1/3</option>
        <option>US 7 &nbsp;&nbsp; - &nbsp; EU 40</option>
        <option>US 7.5 - &nbsp; EU 40 2/3</option>
        <option>US 8 &nbsp;&nbsp; - &nbsp; EU 41 1/3</option>
        <option>US 8.5 - &nbsp; EU 42</option>
        <option>US 9 &nbsp;&nbsp; - &nbsp; EU 42 2/3</option>
        <option>US 9.5 - &nbsp; EU 43 1/3</option>
        <option>US 10 &nbsp;- &nbsp; EU 44</option>
        <option>US 11 &nbsp;- &nbsp; EU 45 1/3</option>
      </select>

      <div v-if="displayErrorMessage" class="error">
        Select a size and a color to add to cart this product.
      </div>
      <div class="buttons">
        <base-button @click="addToCart()" color="signup" class="add-button"
          >Add to cart</base-button
        >
        <base-button
          color="login"
          @click="updateFavorites()"
          class="add-button"
          >{{ contentButtonFavorites }}</base-button
        >
      </div>
    </div>
  </main>
</template>

<script>
import ShoeBox3d from "@/components/ShoeBox3d.vue";
import BaseButton from "@/components/BaseButton.vue";

export default {
  name: "ShoePage",
  components: { ShoeBox3d, BaseButton },
  props: {
    id: {
      type: String,
      require: true,
    },
    color_shoe: {
      type: String,
      default: "white",
    },
  },
  created() {
    this.$store.dispatch("setCurrentShoe", this.id);
  },
  computed: {
    shoe() {
      return this.$store.getters.getCurrentShoe;
    },
    article() {
      const article = {
        color: "",
        size: "",
        id: this.id,
        shoe: this.shoe,
      };
      return article;
    },
    contentButtonFavorites() {
      let content = "Add to favorites";
      const favorites = this.$store.getters.getUser?.favorites;
      if (favorites !== undefined) {
        favorites.forEach((element) => {
          if (element.id === parseInt(this.id)) {
            content = "Remove from favorites";
          }
        });
      }

      return content;
    },
  },
  methods: {
    updateGrade(newGrade) {
      this.$store.dispatch("updateGrade", newGrade);
    },
    addToCart() {
      if (this.article.color && this.article.size) {
        this.$store.dispatch("addToCart", this.article);
        alert(this.shoe.title + " added to cart");
        this.displayErrorMessage = false;
      } else {
        this.displayErrorMessage = true;
      }
    },
    updateFavorites() {
      this.$store.dispatch("updateFavorites", this.id);
    },
    updateColorShoe(color) {
      this.article.color = color;
      this.$router.push({
        name: "ShoePage",
        params: { id: this.id, color_shoe: color },
      });
    },
  },
  data() {
    return {
      //borderColor:"transparent"
      displayErrorMessage: false,
    };
  },
};
</script>

<style scoped>
main {
  padding: 5% 10% 5% 10%;
  display: flex;
  flex-wrap: wrap;
  align-content: center;
}
h3 {
  margin: 0px;
  font-size: 35px;
}
p {
  margin-top: 0px;
  margin-bottom: 15px;
}
.add-button {
  padding: 0.5vh;
  width: 100%;
  text-align: center;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.buttons {
  margin-top: 20px;
  width: 100%;
  display: flex;
  align-items: center;
  height: 12%;
}

.infos {
  border-radius: 4px;
  display: flex;
  flex-wrap: nowrap;
  flex-direction: column;
  width: 40%;
}
.shoe__star {
  color: rgb(251, 206, 25);
}
.shoe__star:hover {
  cursor: pointer;
}
.shoe__star--dark {
  opacity: 0.5;
}
select {
  font-size: large;
  width: fit-content;
  padding: 10px;
  margin-top: 20px;
  width: 100%;
  display: flex;
}
.white {
  width: 25%;
  background-color: white;
  border: 1px solid grey;
}
.blue {
  width: 25%;
  background-color: blue;
}
.red {
  width: 25%;
  background-color: red;
}
.green {
  width: 25%;
  background-color: green;
}
.purple {
  width: 25%;
  background-color: purple;
}
.colors {
  display: flex;
  height: 50px;
  margin-top: 20px;
}
.error {
  color: #ef4444;
  font-size: 0.75rem;
  line-height: 1rem;
  font-style: italic;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
}
</style>
