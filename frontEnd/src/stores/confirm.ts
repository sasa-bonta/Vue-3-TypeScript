import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useConfirmStore = defineStore('confirm', () => {
  const isOpen = ref(false)
  const message = ref('')
  const response = ref<null | boolean>(null)
  let resolvePromise: (value: boolean) => void

  function openConfirm(newMessage: string): Promise<boolean> {
    message.value = newMessage
    isOpen.value = true
    return new Promise<boolean>((resolve) => {
      resolvePromise = resolve
    })
  }

  function closeConfirm(userResponse: boolean) {
    response.value = userResponse
    isOpen.value = false
    if (resolvePromise) {
      resolvePromise(userResponse)
    }
  }

  return { isOpen, message, response, openConfirm, closeConfirm }
})
