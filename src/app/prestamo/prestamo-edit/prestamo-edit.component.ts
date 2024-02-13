import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PrestamoService } from './../prestamo.service';
import { Component, Inject, OnInit } from '@angular/core';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { Game } from 'src/app/game/model/Game';
import { Client } from 'src/app/client/model/Client';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-prestamo-edit',
  templateUrl: './prestamo-edit.component.html',
  styleUrls: ['./prestamo-edit.component.scss'],
})
export class PrestamoEditComponent implements OnInit {

  prestamo: Prestamo;
  games: Game[];
  clients:Client[]
  errorPrestamo = false;

  constructor(
    public dialogRef: MatDialogRef<PrestamoEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private PrestamoService: PrestamoService
  ) {}

  ngOnInit(): void {
    this.prestamo = this.data.prestamo;
    this.games = this.data.games;
    this.clients = this.data.clients;
  }


  onDateChange(event: MatDatepickerInputEvent<Date>, field: string) {
    if (event.value) {
      // Formatear la fecha seleccionada como una cadena en formato 'YYYY-MM-DD'
      const formattedDate = this.formatDate(event.value);
      
      // Asignar la fecha formateada al campo correspondiente en el modelo
      this.prestamo[field] = formattedDate;
    }
  }

  // Función para formatear la fecha en 'YYYY-MM-DD'
  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    
    // Asegurar que el mes y el día tengan dos dígitos
    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedDay = day < 10 ? `0${day}` : day;

    return `${year}-${formattedMonth}-${formattedDay}`;
  }

  onSave() {
    //Check if all the fields are filled
    if (!this.prestamo.game || !this.prestamo.client || !this.prestamo.fechaInicio || !this.prestamo.fechaFin) {
      this.errorPrestamo = true;
      return;
    }

    this.PrestamoService.savePrestamo(this.data.prestamo).subscribe((result) => {
      this.dialogRef.close(result);
    });

  }

  onClose() {
    this.dialogRef.close();
  }
}
