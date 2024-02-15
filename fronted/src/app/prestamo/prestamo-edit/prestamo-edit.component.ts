import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PrestamoService } from './../prestamo.service';
import { Component, Inject, OnInit } from '@angular/core';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { Game } from 'src/app/game/model/Game';
import { Client } from 'src/app/client/model/Client';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { formatDate } from 'src/helpers/date';

@Component({
  selector: 'app-prestamo-edit',
  templateUrl: './prestamo-edit.component.html',
  styleUrls: ['./prestamo-edit.component.scss'],
})
export class PrestamoEditComponent implements OnInit {
  prestamo: Prestamo;
  games: Game[];
  clients: Client[];
  errorPrestamo = false;
  errorDb = '';

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
      const formattedDate = formatDate(event.value);

      // Asignar la fecha formateada al campo correspondiente en el modelo
      this.prestamo[field] = formattedDate;
    }
  }

  onSave() {
    //Check if all the fields are filled
    if (
      !this.prestamo.game ||
      !this.prestamo.client ||
      !this.prestamo.fechaInicio ||
      !this.prestamo.fechaFin
    ) {
      this.errorPrestamo = true;
      return;
    }

    this.PrestamoService.savePrestamo(this.data.prestamo).subscribe(
      {
      next: () => {
        this.dialogRef.close();
      },
      error: (error) => {
        this.errorDb = error.error;
      },
    });
  }

  onClose() {
    this.dialogRef.close();
  }
}
