export const signal = (event) => {
  if (isNaN(event.key)) return false;
  else return true;
};
