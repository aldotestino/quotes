import {reactive} from "vue";

import {server_url} from "@/config";

const state = reactive({
    quotes: [],
    loading: false
});

async function getQuotes() {
    state.loading = true;
    const res = await fetch(server_url);
    state.quotes = await res.json();
    state.loading = false
}

async function searchQuotes(author) {
    state.loading = true;
    const res = await fetch(server_url+author);
    state.quotes = await res.json();
    state.loading = false;
}

async function getRandomQuote() {
    state.loading = true;
    const res = await fetch(server_url+'random');
    state.quotes = [await res.json()];
    state.loading = false;
}

export {state, getQuotes, searchQuotes, getRandomQuote};
