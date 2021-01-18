import {reactive} from 'vue';

function useModal() {
    const state = reactive({
        isOpen: false
    });

    function onOpen() {
        state.isOpen = true;
    }

    function onClose() {
        state.isOpen = false;
    }

    return {
        state,
        onOpen,
        onClose
    }
}

export default useModal;
