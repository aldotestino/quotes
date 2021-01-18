<template>
  <div class="background">
    <div class="modal">
      <div class="modal-header">
        <h2>Submit new quote</h2>
        <button class="modal-close" @click="onClose">
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="modal-content">
        <form @submit.prevent="onSubmit">
          <input type="text" v-model="state.author" placeholder="Author">
          <textarea v-model="state.content" placeholder="Content"></textarea>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {reactive} from 'vue';
import {getQuotes} from "@/store";
import {server_url} from "@/config";

export default {
  name: "QuoteForm",
  props: {
    onClose: Function
  },
  setup(props) {
    const state = reactive({
      author: "",
      content: ""
    });

    async function onSubmit() {
      const newQuote = {
        author: state.author.trim(),
        content: state.content.trim(),
      }
      if(newQuote.author === '' || newQuote.content === '') {
        return;
      }
      await fetch(server_url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newQuote)
      });
      await getQuotes();
      props.onClose()
    }

    return {
      state,
      onSubmit
    }
  }
}
</script>

<style scoped lang="scss">
.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.5);
  backdrop-filter: blur(1px);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal {
  background-color: #2D3748;
  border-radius: 10px;
  padding: 20px;
  width: 90%;
  max-width: 500px;
}

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;

  h2 {
    font-family: 'Roboto', sans-serif;
    color: white;
  }

  .modal-close {
    height: 38px;
    width: 38px;
  }
}

.modal-content form {
  display: flex;
  flex-direction: column;

  input, textarea {
    background: transparent;
    font-size: 20px;
    font-family: 'Roboto', sans-serif;
    margin-bottom: 10px;
    border-radius: 5px;
    border: lightslategray 2px solid;
    padding: 5px;
    outline: none;
    transition: border-color .2s linear;
    width: 100%;
    color: white;
    &:focus {
      border-color: #38B2AC;
    }
    &::placeholder {
      color: rgba(255,255,255,0.7);
    }
  }

  textarea {
    min-height: 200px;
    resize: vertical;
    max-height: 400px;
  }
}
</style>