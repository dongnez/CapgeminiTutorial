import { Component, OnInit } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from 'src/app/client/client.service';
import { Client } from 'src/app/client/model/Client';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { PrestamoEditComponent } from 'src/app/prestamo/prestamo-edit/prestamo-edit.component';
import { PrestamoService } from 'src/app/prestamo/prestamo.service';
import { formatDate } from 'src/helpers/date';

@Component({
  selector: 'app-prestamo-list',
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.scss'],
})
export class PrestamoListComponent implements OnInit {
  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  prestamosDataSource = new MatTableDataSource<Prestamo>();
  games: Game[];
  clients: Client[];

  displayedColumns: string[] = [
    'id',
    'game',
    'client',
    'fechaInicio',
    'fechaFin',
    'action',
  ];
  filterClient: Client;
  filterGame: Game;
  filterDate: String;

  constructor(
    private prestamoService: PrestamoService,
    private gameService: GameService,
    private clientService: ClientService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPage();

    this.gameService.getGames().subscribe((data) => {
      this.games = data;
    });

    this.clientService.getClients().subscribe((data) => {
      this.clients = data;
    });
  }

  onCleanFilter(): void {
    this.filterClient = null;
    this.filterGame = null;
    this.filterDate = null;

    this.onSearch();
  }

  onDateChange(event: MatDatepickerInputEvent<Date>, field: string) {
    if (event.value) {
      // Formatear la fecha seleccionada como una cadena en formato 'YYYY-MM-DD'
      const formattedDate = formatDate(event.value);

      // Asignar la fecha formateada al campo correspondiente en el modelo
      this.filterDate = formattedDate;
    }
  }

  onSearch(): void {
    this.loadPage();
  }

  loadPage(event?: PageEvent) {
    let pageable: Pageable = {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      sort: [
        {
          property: 'id',
          direction: 'ASC',
        },
      ],
    };

    if (event != null) {
      pageable.pageSize = event.pageSize;
      pageable.pageNumber = event.pageIndex;
    }


    this.prestamoService
      .getPrestamos(pageable, {
        idGame: this.filterGame != null ? this.filterGame.id : null,
        idClient: this.filterClient != null ? this.filterClient.id : null,
        date: this.filterDate,
      })
      .subscribe((data) => {
        this.prestamosDataSource.data = data.content;
        this.pageNumber = data.pageable.pageNumber;
        this.pageSize = data.pageable.pageSize;
        this.totalElements = data.totalElements;
      });
  }

  createPrestamo() {
    const dialogRef = this.dialog.open(PrestamoEditComponent, {
      width: '500px',
      data: {
        prestamo: new Prestamo(),
        games: this.games,
        clients: this.clients,
      },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  deletePrestamo(prestamo: Prestamo) {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar Prestamo',
        message: `¿Está seguro de que desea eliminar el prestamo ${prestamo.id}?`,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.prestamoService.deletePrestamo(prestamo.id).subscribe((data) => {
          this.ngOnInit();
        });
      }
    });
  }
}
