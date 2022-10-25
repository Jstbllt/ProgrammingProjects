<template>
    <div class="box">
    <img :src="shoe.src" alt="">
    <div class="model">
      <Renderer ref="renderer" orbit-ctrl >
        <Camera :position="{ x: shoe.obj3DSettings.cameraPosition[0],y: shoe.obj3DSettings.cameraPosition[1], z: shoe.obj3DSettings.cameraPosition[2] }" />
        <Scene background="whitesmoke">
          <AmbientLight></AmbientLight>
          <PointLight :color="color"  :intensity="shoe.obj3DSettings.intensity" :position="{ x: shoe.obj3DSettings.cameraPosition[0],y: shoe.obj3DSettings.cameraPosition[1], z: shoe.obj3DSettings.cameraPosition[2]  }" />
          <PointLight :color="color"  :intensity="shoe.obj3DSettings.intensity" :position="{ x: -shoe.obj3DSettings.cameraPosition[0],y: shoe.obj3DSettings.cameraPosition[1], z: -shoe.obj3DSettings.cameraPosition[2]  }" />
          <PointLight :color="color"  :intensity="shoe.obj3DSettings.intensity" :position="{ x: shoe.obj3DSettings.cameraPosition[0],y: shoe.obj3DSettings.cameraPosition[1], z: -shoe.obj3DSettings.cameraPosition[2]  }" />
          <PointLight :color="color"  :intensity="shoe.obj3DSettings.intensity" :position="{ x: -shoe.obj3DSettings.cameraPosition[0],y: shoe.obj3DSettings.cameraPosition[1], z: shoe.obj3DSettings.cameraPosition[2]  }" />
          <PointLight :color="color"  :intensity="shoe.obj3DSettings.intensity" :position="{ x: 0,y: -shoe.obj3DSettings.cameraPosition[1], z:0}" />
          <GltfModel ref="model"
          :src="shoe.obj3DSettings.link"
          @load="onReady"
          />
        </Scene>
      </Renderer>
    </div>
    </div>
</template>

<script>



export default {
  components: { },
  name: "ShoeBox3d",
  props:{
    shoe:{
      type:Object,
      required: true
    },
    color:{
      type:String,
      default:"white"
    }
  },
  
   mounted() {
    this.$refs.renderer.three.setSize(500, 450)

   },
   setup(){
    function onReady() {
        console.log("Render ready")
    }
    return {
        onReady
    }
   }
}
</script>

<style scoped>
  .box{
    width: 550px;
  }
  img{
    display: none;
  }
  @media screen and (max-width: 680px) {
    img{
      display:block;
      width: 100%;
    }
    .model{
      display: none;
    }
  }
</style>
   

