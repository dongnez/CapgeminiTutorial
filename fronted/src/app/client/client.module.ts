import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientItemComponent } from './client-list/client-item/client-item.component';
import { ClientEditComponent } from './client-edit/client-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';



@NgModule({
  declarations: [
    ClientListComponent,
    ClientItemComponent,
    ClientEditComponent,

  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule, 
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ]
})
export class ClientModule { }
