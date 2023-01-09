<template>
  <div>
    <h1>store.state.name: {{ $store.state.name }}</h1>
    <el-button @click="addStore">add</el-button>
    <el-button @click="clearStore">clear</el-button>
    <el-button @click="getStore">get</el-button>
  </div>
</template>

<script>
export default {
  name: "VuexTest",
  methods: {
    addStore(){
      let localRoutes = JSON.parse(localStorage.getItem('dynamicMenuRoutes'))
      this.$store.commit("setDynamicMenuRoutes", localRoutes)
      this.$store.commit("setName", '张三')
      console.log(this.$store.state.dynamicMenuRoutes)
    },
    clearStore(){
      this.$store.commit("clearDynamicMenuRoutes")
      this.$store.commit("clearName")
      console.log(this.$store.state.dynamicMenuRoutes)
    },
    getStore(){
      console.log(this.$store.state.dynamicMenuRoutes)
      console.log(this.$store.state.name)
    }
  }
}
</script>

<style scoped>

</style>