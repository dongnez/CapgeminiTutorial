import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrestamoListComponent } from './prestamo-list/prestamo-list.component';
import { PrestamoItemComponent } from './prestamo-list/prestamo-item/prestamo-item.component';
import { PrestamoEditComponent } from './prestamo-edit/prestamo-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';


@NgModule({
  declarations: [
    PrestamoListComponent,
    PrestamoItemComponent,
    PrestamoEditComponent,
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule, 
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatOptionModule,
    MatPaginatorModule,
  ]
})
export class PrestamoModule { }
