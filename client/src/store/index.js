import {reactive} from "vue";

import {server_url} from "@/config";

const state = reactive({
    quotes: []
});

async function getQuotes() {
    const res = await fetch(server_url);
    state.quotes = await res.json();
}

async function searchQuotes(author) {
    const res = await fetch(server_url+author);
    state.quotes = await res.json();
}

async function getRandomQuote() {
    const res = await fetch(server_url+'random');
    state.quotes = [await res.json()];
}

export {state, getQuotes, searchQuotes, getRandomQuote};
