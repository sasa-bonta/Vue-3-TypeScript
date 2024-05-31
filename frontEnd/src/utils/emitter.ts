import mitt from 'mitt'

type Events = {
  'notify-success': string
  'notify-error': string
  'confirm-dialog': string
  'confirm-response': boolean
}

const emitter = mitt<Events>()

export default emitter
