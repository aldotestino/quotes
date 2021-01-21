import {reactive} from 'vue';

function useModal() {
    const state = reactive({
        isOpen: false
    });

    function onOpen() {
        document.body.style.overflowY = 'hidden';
        state.isOpen = true;
    }

    function onClose() {
        document.body.style.overflowY = 'auto';
        state.isOpen = false;
    }

    return {
        state,
        onOpen,
        onClose
    }
}

export default useModal;
