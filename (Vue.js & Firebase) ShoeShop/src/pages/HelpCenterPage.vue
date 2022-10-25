<template>
    <h1>Help Center</h1>
    <h2>To contact us please select the subject of ou request.</h2>
    <button id="ReturnButton" @click="selectSubject='',  selectPurchase=false, selectAccount=false, selectIdeas=false">Return</button>
    <div id="listButtons">
        <button v-if="!selectAccount && !selectIdeas && !selectPurchase" @click="selectPurchase=true">Purchase</button>
            <button v-if="selectPurchase && selectSubject===''" @click="selectSubject = 'Purchase general process'">Purchase general process</button>
            <button v-if="selectPurchase && selectSubject===''"  @click="selectSubject = 'Current order'">Current order</button>
            <button v-if="selectPurchase && selectSubject===''"  @click="selectSubject = 'Return and exanges'">Return and exanges</button>
        <button v-if="!selectAccount && !selectIdeas && !selectPurchase" @click="selectAccount = true">Account And Parameters</button>
            <button v-if="selectAccount && selectSubject===''" @click="selectSubject = 'Account Blocks, Restrictions, or Suspension'" >Account Blocks, Restrictions, or Suspension</button>
            <button v-if="selectAccount && selectSubject===''" @click="selectSubject = 'Login / Password'" >Login / Password</button>
            <button v-if="selectAccount && selectSubject===''" @click="selectSubject = 'Delete an account'" >Delete an account</button>
            <button v-if="selectAccount && selectSubject===''" @click="selectSubject = 'Profiles and settings'">Profiles and settings</button>
        <button v-if="!selectAccount && !selectIdeas && !selectPurchase" @click="selectIdeas = true">Ideas And Remarks</button>
            <button v-if="selectIdeas && selectSubject===''" @click="selectSubject = 'Customer service'">Customer service</button>
            <button v-if="selectIdeas && selectSubject===''" @click="selectSubject = 'Navigation and catalog'" >Navigation and catalog</button>
            <button v-if="selectIdeas && selectSubject===''" @click="selectSubject = 'Feature Requests / Suggestion'" >Feature Requests / Suggestion</button>
    </div>
    <form v-if="selectSubject !== ''" @submit="sendEmail()" >
        <div>
            <div id="selectSubject">Selected subject: {{selectSubject}}</div>
        </div>
        <div>
            <label for="description">Description</label><br/>
            <textarea id="description" name="description" v-model="description" rows="10" cols="60"></textarea>
        </div>
        <div>
            <input type="submit" value="Submit">
        </div>
    </form>
    <div id='space'></div>
</template>


<script>
export default {
    components: {},
    name: "HelpCenter",
    data() {
        return {
            selectPurchase: false,
            selectAccount:false,
            selectIdeas: false,
            blind : false,
            selectSubject : "",
            email : "",
            description :"",
        };
    },
    methods:{
        sendEmail(){
            window.open('mailto:helpcenter@shoeshop.com?subject='+this.selectSubject+'&body='+this.description);
        }
    }
        
}
</script>


<style scoped>
    h1{
        text-align: center;
    }
    h2{
        text-align: center;
        color : rgb(100,117,140);
        font-size: large;
    }
    #listButtons{
        display : flex;
        flex-direction: column;
        margin : 2%;
    }
    #listButtons button{
        margin-top : 2%;
        background: white;
        border : none;
        border-bottom: 1px solid gray;
        font-size : x-large;
        color : gray;
        padding : 1%;
    }
    #listButtons button:hover{
        transition: 2ms;
        border-bottom: 1px solid black;
        color : black;
    }
    #ReturnButton{
        background: white;
        margin-left:2%;
        color: rgb(100,117,140);
        text-decoration: underline rgb(100,117,140);
        border : none;
        font-size: large;
    }
    #ReturnButton:hover{
        color: black;
        text-decoration: underline black;
    }
    form{
        text-align: center;
        font-size : large;
        border : 1px solid  rgb(100,117,140);
        padding : 1%;
        margin : 2%;
        margin-left : 10%;
        margin-right : 10%;
    }
    form div{
        padding : 1%;
        font-size : larger;
    }
    form input{
        padding : 0.5%;
    }
    form  #selectSubject{
        padding : 0.5%;
        color:  rgb(100,117,140);
    }
    #space{
        margin-bottom: 8%;
    }
</style>