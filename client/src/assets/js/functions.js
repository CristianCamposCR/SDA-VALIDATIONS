
export const onSimulateShow = () => new Promise(resolve => setTimeout(() => resolve(true), 500))
export const onSimulateSend = () =>  new Promise(resolve => setTimeout(() => resolve(false), 2000))