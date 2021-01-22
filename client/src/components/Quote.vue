<template>
  <div class="quote">
    <header class="quote-header">
      <button class="quote-delete__button" @click="removeQuote"><i class="fas fa-trash"></i></button>
      <h1>{{quote.author}}</h1>
    </header>
    <p>{{quote.content}}</p>
  </div>
</template>

<script>
import {server_url} from "@/config";
import {getQuotes} from "@/store";

export default {
  name: "Quote",
  props: {
    quote: {
      author: String,
      content: String,
      id: String
    }
  },
  setup(props) {
    async function removeQuote() {
      await fetch(server_url+props.quote.id, {
        method: 'DELETE'
      });
      await getQuotes();
    }

    return {
      removeQuote
    }
  }
}

</script>

<style scoped lang="scss">
.quote {
  color: white;
  font-family: 'Roboto', sans-serif;

  .quote-header {
    padding: 0;
    margin-bottom: 10px;
    display: flex;
    align-items: center;

    .quote-delete__button {
      height: 38px;
      width: 38px;
      margin-right: 20px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }

  p {
    margin-left: 58px;
    border-left: 2px solid white;
    padding-left: 20px;
    font-size: 20px;
  }
}
</style>