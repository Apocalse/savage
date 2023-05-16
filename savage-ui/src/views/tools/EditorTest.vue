<template>
  <div>
    <quill-editor
        ref="text"
        v-model="content"
        class="myQuillEditor"
        :options="editorOption"
    />
    <el-button type="primary" @click="submit">提交</el-button>
    <div v-html="data"></div>
  </div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
export default {
  name: "EditorTest",
  components: {
    quillEditor
  },
  data() {
    return {
      content: "",
      editorOption: {},
      data:''
    };
  },
  methods: {
    submit() {
      this.data = this.$refs.text.value
      console.log(this.$refs.text.value);
    },
  },
}
</script>

<style scoped>

</style>
