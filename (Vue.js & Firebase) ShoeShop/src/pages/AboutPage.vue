<template>
    <main>
      <div id="trendMarket">
        <div>
          <h1>The Trend Market </h1>
          <p>
            Our mission is to provide access to personalized sneakers in the most intelligent way possible.
            ShoeShop addresses everyone and shares its passion for the sneaker, much more than a trend, a true state of mind that transcends generations. The brand wants to open up the sneaker world and invites all consumers to share its universe.
          </p>
        </div>
        <img src="../assets/image-chaussure3D.jpg">
      </div>

      <div id="us-box">
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-shoe-prints"/>
            <h1>Verified by ShoeShop</h1>
            <p>
              Every item sold undergoes our exclusive multi-step verification process by our team of authentication experts.
            </p>
        </div>
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-money-bill"/>
            <h1>Price transparency</h1>
            <p>
              Our real-time platform works like the stock market, allowing you to buy the most coveted items at their fair price.
            </p>
        </div>
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-earth-europe"/>
            <h1>Global Access</h1>
            <p>
              Whether it's pre-orders, limited editions or out-of-stock items, our team secures coveted and hard-to-find items.
            </p>
        </div>
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-shield-halved"/>
            <h1>No useless blah blah blah</h1>
            <p>
              No chargebacks, no photos and no crazy descriptions. We take care of everything so you can buy with confidence.
            </p>
        </div>
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-lock"/>
            <h1>Secure</h1>
            <p>
              Maintaining the integrity of our platform means staying ahead of the curve. Our security and anti-fraud systems ensure your personal information is protected 24 hours a day, 7 days a week.
            </p>
        </div>
        <div class="card">
          <font-awesome-icon icon="fa-solid fa-message fa-10x"/>
            <h1>At your service</h1>
            <p>
              With our Help Center, and our global support team, you can be sure that we are always available to answer any questions about our platform.
            </p>
        </div>
      </div>
      <div id="help">
        <h1>Welcome into the center help of ShoeShop</h1>
        <h2>How can we help you?</h2>
        <form action="/#/helpCenter">
          <input type="text" placeholder="Ask a question"  required>
        </form>
      </div>

    </main>
</template>

<script>

export default {
  components: {},
  name: "AboutPage"
}
</script>

<style scoped>
  main{
    text-align: center;
  }

  #trendMarket{
    display: flex;
    width: 100%;
    background: black;
    justify-content: space-evenly;
    align-items: center;
  }
  #trendMarket img{
    width: 40%;
  }
  #trendMarket div{
    width: 30%;
    color : white;
    text-align: justify;
  }
  #us-box{
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
  }
  .card{
    margin : 2%;
    padding : 1%;
    width: 20%;
  }
  .card .svg-inline--fa{
    font-size:3.000em; 
    color :rgb(100,117,140);
  }
  #help{
    background: black;
    color:white;
    text-align: justify;
    padding: 1%;
    height: 250px;
    display:flex;
    flex-direction: column;
    padding-left: 5%;
    padding-top: 2%;
  }
  #help input{
    font-size:medium; 
    width: 60%;
    padding : 10px;
    border-radius: 5px;
  }
  div h1{
    font-size : x-large;
    font-weight: bold;
  }
  div p{
    font-size:small;
  }

</style>
