<template>
  <main :category_shoe="category_shoe">
    <div class="en-tete">
      <h2>{{ category_shoe }}</h2>
      <p v-if="category_shoe!='Sneakers'">Buy the best {{category_shoe}} shoes. We have all the models, silhouettes and collaborations, in all the colours and sizes below. Enjoy your shopping!</p>
      <p v-else>Buy the best shoes. We have all the models, silhouettes and collaborations, in all the colours and sizes below. Enjoy your shopping!</p>
    </div>
    <div class="main_content">
      <div class="one">
        <ul>
          <li><base-button @click="updateCategoryShoe('Sneakers')"><h3>SNEAKERS</h3></base-button></li>
          <li v-for="(brand) in brands" v-bind:key="brand">
            <base-button @click="updateCategoryShoe(brand)"><h3>{{brand}}</h3></base-button>
          </li>
        </ul>
      </div>
      <div class="three">
        <span><base-button direction="Home">Home</base-button>/<base-button @click="updateCategoryShoe('Sneakers')">Sneakers</base-button> <span v-if="category_shoe!='Sneakers'">/<base-button >{{category_shoe}}</base-button></span></span>
      </div>
      <section class="list two">
        <template v-for="(shoe) in shoes">
          <shoe-box v-if="category_shoe=='Sneakers'"
                    v-bind:key="shoe.brand"
                    class="box"
                    :shoe="shoe"/>
          <shoe-box
              v-else-if="shoe.brand==category_shoe"
              v-bind:key="shoe.brand"
              class="box"
              :shoe="shoe"/>
        </template>
      </section>
    </div>
  </main>
</template>

<script>

import ShoeBox from '@/components/ShoeBox.vue'
import BaseButton from "@/components/BaseButton";

export default {
  components:{BaseButton, ShoeBox},
  name: "BrowsePage",
  props: {
    category_shoe: {
      type: String,
      default: "Sneakers",
    },
  },
  computed:{
    shoes(){
      return this.$store.getters.getShoes
    },
    brands(){
      return this.$store.getters.getBrands
    },
  },
  methods: {
    updateCategoryShoe(category) {
      this.$router.push({
        name: "BrowsePage",
        params: {category_shoe: category},
      });
    },
  },

}
</script>

<style scoped>

.en-tete{
  padding: 2%;
  margin: 2% 4%;
  background-color: #374151;
  color: whitesmoke;
}

.en-tete h2{
  margin: 0;
}

.en-tete p {
  font-size: small;
}

.main_content{
  padding-left: 3%;
  padding-right: 3%;
  display: grid;
  grid-template-columns: 1fr 4fr;
  grid-template-rows: 4vh 1fr;
}
.one {
  grid-column: 1 / 2;
  grid-row: 1;
}
.two {
  grid-column: 2 / 2;
  grid-row: 2 / 2;
}

.three{
  grid-column: 2 / 2;
  grid-row: 1 / 2;
}

.box{
  border: solid black 1px;
  margin: 3% 1%;
  width: 28%;
  padding: 1.5%;
  display: flex;
  flex-direction: column;

}
.list{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

ul{
  list-style: none;
}

ul h3 {
  margin: 0;
}

@media screen and (max-width: 800px){
  .one{
    display : flex;
    
  }
  ul{
    margin : 0;
    display:flex;
    padding-inline-start: 0px;
    padding-inline-start: 0px;
    width: 100%;
  }
  li{
    margin : 1%;
    width: 25%;
    font-size: xx-small;
    justify-content: center;
  }
  .main_content{
    display : block;
    grid-template-columns : 0;
    grid-template-rows:0;
  }

  @media screen and (max-width: 650px){
    .box{
      width: 40%;
    }
  }

  @media screen and (max-width: 500px){
    .box{
      width: 70%;
    }
    li{
      font-size: 40%;
    }
  }
}

</style>