<template>
  <header class="navbar">
    <a href="/">Quotes</a>
    <div class="right">
      <form @submit.prevent="handleSearch">
        <div class="input-container">
          <i class="fas fa-search"></i>
          <input type="text" v-model="query.current" placeholder="Search quotes">
        </div>
      </form>
      <button class="add-button" @click="onOpen">
        <i class="fas fa-plus"></i>
      </button>
    </div>
  </header>
  <main v-if="state.quotes.length !== 0 && !state.loading">
    <Quote v-for="quote in state.quotes" :quote="quote" :key="quote.id"></Quote>
  </main>
  <main v-else-if="!state.loading" class="no-quotes">
    <h1>No results for "{{query.last}}" 😢</h1>
    <button @click="getQuotes">Get all quotes</button>
  </main>
  <transition name="fade">
      <QuoteForm v-if="modalState.isOpen" :onClose="onClose"></QuoteForm>
  </transition>
</template>

<script>
import Quote from "@/components/Quote";
import QuoteForm from "@/components/QuoteForm";
import {onMounted, reactive} from 'vue';
import useModal from "@/hooks/useModal";
import {state, getQuotes, searchQuotes,getRandomQuote} from "@/store";

export default {
  name: 'App',
  components: {
    QuoteForm,
    Quote
  },
  setup() {

    const query = reactive({
      current: '',
      last: ''
    });

    const {state: modalState, onOpen, onClose} = useModal();

    onMounted(async () => {
      await getRandomQuote();
    });

    async function handleSearch() {
      if(query.current === '') {
        await getQuotes();
      }
      await searchQuotes(query.current);
      query.last = query.current;
      query.current = '';
    }

    return {
      state,
      modalState,
      onOpen,
      onClose,
      query,
      handleSearch,
      getQuotes,
    }
  }
}
</script>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  background-color: #2D3748;
}

.navbar {
  position: sticky;
  top: 0;
  background-color: rgba(#2D3748, 0.8);
  backdrop-filter: blur(5px);
  box-shadow: 0 10px 20px rgba(#000, 0.2);
  z-index: 2;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 300px;

  .right {
    display: flex;
    align-items: center;
    justify-content: space-around;

    .add-button {
      height: 38px;
      width: 38px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .input-container {
      margin-right: 10px;
      border-radius: 5px;
      border: lightslategray 2px solid;
      padding: 5px;
      transition: all .2s linear;
      width: 100px;
      display: flex;
      align-items: center;
      &:focus-within {
        border-color: #38B2AC;
        width: 175px;
        i {
          color: white;
        }
      }

      i {
        transition: all .2s linear;
        color: rgba(255,255,255,0.7);
      }

      input {
        margin-left: 5px;
        border: none;
        background: transparent;
        font-family: 'Roboto', sans-serif;
        color: white;
        outline: none;
        width: 100%;
        font-size: 20px;
        &::placeholder {
          color: rgba(255,255,255,0.7);
        }
      }
    }
  }

  a {
    font-family: 'Roboto', sans-serif;
    color: white;
    font-size: 32px;
    text-decoration: none;
  }
}

.no-quotes {
  color: white;
  font-family: 'Roboto', sans-serif;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

main {
  padding: 30px 300px;
  display: grid;
  grid-row-gap: 40px;
}

button {
  font-size: 20px;
  border-radius: 5px;
  border: none;
  padding: 7.5px 10px;
  background-color: #38B2AC;
  cursor: pointer;
  color: white;
  outline: none;
  transition: all .2s linear;
  &:hover {
    background-color: darken(#38B2AC, 5);
  }
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

@media screen and (max-width: 1600px) {
  .navbar {
    padding: 10px 200px;
  }
  main {
    padding: 30px 200px;
  }
}

@media screen and (max-width: 1300px) {
  .navbar {
    padding: 10px 100px;
  }
  main {
    padding: 30px 100px;
  }
}

@media screen and (max-width: 1000px) {
  .navbar {
    padding: 10px 50px;
  }
  main {
    padding: 30px 50px;
  }
}

@media screen and (max-width: 750px) {
  .navbar {
    padding: 10px 30px;
  }
  main {
    padding: 30px 30px;
  }
}

@media screen and (max-width: 500px) {
  .navbar {
    padding: 10px 10px;
  }
  main {
    padding: 30px 10px;
  }
}
</style>
