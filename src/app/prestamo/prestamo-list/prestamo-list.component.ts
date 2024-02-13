import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from 'src/app/client/client.service';
import { Client } from 'src/app/client/model/Client';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { PrestamoEditComponent } from 'src/app/prestamo/prestamo-edit/prestamo-edit.component';
import { PrestamoService } from 'src/app/prestamo/prestamo.service';

@Component({
  selector: 'app-prestamo-list',
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.scss'],
})
export class PrestamoListComponent implements OnInit {
  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  dataSource = new MatTableDataSource<Prestamo>();
  games:Game[];
  clients:Client[];

  displayedColumns: string[] = [
    'id',
    'game',
    'client',
    'fechaInicio',
    'fechaFin',
    'action'
  ];

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

    this.prestamoService.getPrestamos(pageable).subscribe((data) => {
      this.dataSource.data = data.content;
      this.pageNumber = data.pageable.pageNumber;
      this.pageSize = data.pageable.pageSize;
      this.totalElements = data.totalElements;
    });
  }

  createPrestamo() {
    const dialogRef = this.dialog.open(PrestamoEditComponent, {
      width: '500px',
      data: {prestamo:new Prestamo(),games:this.games, clients:this.clients}
    });

    dialogRef.afterClosed().subscribe((result) => {
        console.log(result);
        this.ngOnInit();

    });
  }

  editPrestamo(prestamo: any) {}

  deletePrestamo(prestamo: any) {}
}
