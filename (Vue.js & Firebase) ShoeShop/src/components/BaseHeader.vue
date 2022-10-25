<template>
    <header>
      <nav>
        <base-button direction='Home'><img src="../assets/shoeshop.png" class="logo" alt="logo"></base-button>
        <div @mouseover="upHere = false" class="input-group">
          <font-awesome-icon icon="fa-solid fa-search"/> 
          <input type="text" placeholder="Search brand, color, ..." >
        </div> 	
        <label for="toggle">☰</label>
        <input type="checkbox" id="toggle">
        <div id="main_pages" class="menu">
          <base-button direction='BrowsePage' :params="{category_shoe: 'Sneakers'}" @mouseover="upHere = true" @click="upHere = false" class="browse_link">Browse</base-button>
          <base-button direction="News">News</base-button>
          <base-button direction='About'>About</base-button>
          <base-button direction='HelpCenter'>Help</base-button>
          <base-button v-if="$store.state.user" direction='Cart' ><font-awesome-icon icon="fa-solid fa-basket-shopping"/></base-button>
          <base-button v-if="$store.state.user" direction="Favorites" ><font-awesome-icon icon="fa-solid fa-heart"/></base-button>
          <base-button v-if="$store.state.user" direction='Account' ><font-awesome-icon icon="fa-solid fa-user"/></base-button>
          <base-button v-if="!$store.state.user" color="login" direction='Login' >Login</base-button>
          <base-button v-if="!$store.state.user" color="signup" direction='Register' >Sign up</base-button>
        </div>
      </nav>
    </header>

    <div v-show="upHere" @mouseover="upHere = true" @mouseleave="upHere = false" class="browse_bar" id="browse_bar">
      <base-button @click="upHere = false" class="no_margin_button" direction="ShoePage" :params="{id:4}">
        <div class="new_releases">
          <div class="newR_components">
            <h3>New Releases</h3>
            <img src="../assets/sneaker.png">
            <p>New models are here! Click to discover.</p>
          </div>
        </div>
      </base-button>

      <div class="list">
        <h4>Brand</h4>

        <ul v-for="(brand) in brands"
            v-bind:key="brand">
          <li><base-button @click="upHere = false" class="no_margin_button" direction='BrowsePage' :params="{category_shoe: brand}" color="simple">{{brand}}</base-button></li>
        </ul>
      </div>

      <div class="list">
        <h4>Color</h4>
        <ul>
          <li>Black</li>
          <li>White</li>
          <li>Marine Blue</li>
          <li>Brown</li>
          <li>Dark Green</li>
          <li>Red</li>
        </ul>
      </div>
    </div>
</template>

<script>
import BaseButton from "./BaseButton.vue";
import { onBeforeMount } from "vue";
import { useStore } from "vuex";

export default {
  components: { BaseButton },
  name: "BaseHeader",
  computed:{
    shoes(){
      return this.$store.getters.getShoes
    },
    brands(){
      return this.$store.getters.getBrands
    },
  },
  setup() {
    const store = useStore();
    onBeforeMount(() => {
      store.dispatch("fetchUser");
    });
  },
  data: () => ({ upHere: false }),
};
</script>

<style scoped>
nav {
  overflow: hidden;
  border-bottom: solid gray 1px;
  padding: 1%;
}

input,
input:hover {
  border: none;
  outline: none;
  padding-left: 10px;
  width: 16em;
}

.input-group {
  top: 0;
  padding: 8px;
  border: 1px solid;
  border-radius: 3px;
  margin-left: 2%;
  color: gray;
}

nav div {
  display: inline-block;
}

.menu {
  float: right;
  width: 28em;
  text-align: left;
  display: inline-flex;
}

.logo {
  width: 150px;
  vertical-align: middle;
}
#bellicon {
  padding: 2%;
}
label,
#toggle {
  display: none;
}

@media screen and (max-width: 952px) {
  nav #main_pages {
    display: none;
  }
  label {
    width: 30px;
    font-size: 35px;
    color: rgb(100, 117, 140);
    float: right;
    margin-right: 1em;
  }
  nav label {
    display: inline-block;
    vertical-align: middle;
  }
  nav #main_pages {
    flex-direction: column;
  }
  .menu {
    float: middle;
    width: 100%;
    vertical-align: middle;
    text-align: middle;
  }
  #toggle:checked + #main_pages {
    display: flex;
  }

  @media screen and (max-width: 528px) {
    .input-group {
      display: none;
    }
  }
}

.browse_link:hover {
  border-bottom: 2px solid rgb(100, 117, 140);
}

.browse_bar {
  padding-left: 4%;
  padding-right: 4%;
  padding-bottom: 2%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  border-bottom: solid gray 1px;
}

.browse_bar ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

.browse_bar .list {
  padding-right: 3%;
  padding-left: 3%;
}

.browse_bar .list h4 {
  border-bottom: 2px solid rgb(100, 117, 140);
}

.new_releases {
  background-image: url("../assets/air-jordan-4-offwhite.jpg");
  background-clip: content-box;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  border-radius: 5px;
  margin-right: 4%;
  margin-top: 2%;
  display: flex;
  flex-direction: row;
}

.new_releases img {
  width: 35px;
  height: auto;
}

.newR_components {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  border-radius: 5px;
  background: rgba(0, 0, 0, 0.7);
  padding: 3%;
  color: white;
  height: 91%;
  width: 100%;
}

.newR_components h3 + p {
  margin: 0;
}
.no_margin_button{
  margin: 0;
}

</style>
