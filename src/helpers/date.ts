  // Función para formatear la fecha en 'YYYY-MM-DD'
  export function formatDate(date: Date): string {

    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    
    // Asegurar que el mes y el día tengan dos dígitos
    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedDay = day < 10 ? `0${day}` : day;

    return `${year}-${formattedMonth}-${formattedDay}`;
  }