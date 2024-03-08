export const signal = (event) => {
  if (isNaN(event.key) && onlyPositive(event)) return false;
  else return true;
};

export const onlyPositive = (event) => {
  if (event.keyCode >= 48 && event.keyCode <= 57) {
    return true; // Permitir dígitos del 0 al 9
  } else {
    event.preventDefault(); // Previene la acción predeterminada del evento
    return false; // Rechazar cualquier otro carácter
  }
}
