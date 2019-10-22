<template>
  <div id="app">
    <el-button type="primary">主要按钮</el-button>
  </div>
</template>

<script>
import Vue from 'vue';
import { Button, Select } from 'element-ui';

Vue.component(Button.name, Button);

export default {
  name: 'app',
  components: {
    
  }
}
</script>
