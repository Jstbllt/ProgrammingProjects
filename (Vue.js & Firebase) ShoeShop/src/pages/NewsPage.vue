<template>
  <main>
    <h1>All articles about Sneakers from the last month, sorted by recent first</h1>
    <section class="research">
      <input 
        type="text" 
        placeholder="Search among articles"
        v-model="search" 
      />
			<base-button class="button" color="login" @click="searchArticle">Search</base-button>
			<base-button class="button" color="signup" @click="getArticles">Reset research</base-button>
    </section>
    <section class="articles">
      <div
        class="article"
        v-for="article in articles"
        v-bind:key="article.title"
        >
        <div>
          <a :href="article.url">{{article.title}}</a>
          <p>{{article.description}}</p>
          <h2>Published at {{article.publishedAt}} by {{article.author}} on {{article.source.name}}</h2>
        </div>
        <img :src="article.urlToImage" alt="">
      </div>
    </section>
  </main>
</template>

<script>
import {getArticles} from '../services'
import { ref } from 'vue'
import BaseButton from '@/components/BaseButton.vue';
export default {
  name:"NewsPage",
  components:{BaseButton},
  data(){
    return {
      articles:this.getArticles()
      }
  },
  setup(){
    const search = ref();
    return{
      search
    }
  },
  methods:{
    getArticles(){
      getArticles().then((result)=>{
        this.articles = result.data.articles
      }) 
    },
    searchArticle(){
      let newSearch = []
      this.articles.forEach(article => {
        console.log(this.search)
        if(article.description.toLowerCase().includes(this.search.toLowerCase())){
          newSearch.push(article)
        }
      });
      this.articles=newSearch
    }
  }
}
</script>

<style scoped>
  main{
    padding: 0% 15% 0% 15%;
  }
  h1{
    text-align: center;
    font-size: 300%;
    font-family: "Marr Sans Condensed";
    font-weight: bold;
  }
  img{
    width: 180px;
  }
  .article{
    border-bottom: solid gray 1px;
    padding: 20px 0px 20px 0px;
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: space-between;
  }
  @media screen and (max-width: 750px) {
    .article{
      display: block;
    }
    .research{
    flex-direction: column;
  }
  }
  .articles{
    background-color: whitesmoke;
    padding:30px
  }
  .article div{
    margin-right: 5%;
  }
  
  .article div a{ 
    font-size: 25px;
    text-decoration: none;
    color: black;
    font-weight:bold;
  }
  .article div h2{ 
    font-size: 14px;
    color: gray;
    font-weight: 200;
  }
  .article div  a:hover{ 
    text-decoration: underline;
  }
  .article div  p{ 
    text-align: justify;
  }
  .research{
    height: fit-content;
    margin: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .research input{
    height: 100%;
    padding-left: 10px;
  }
  .button{
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>