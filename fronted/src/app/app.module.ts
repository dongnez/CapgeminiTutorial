import { PrestamoModule } from './prestamo/prestamo.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from 'src/app/core/core.module';
import { CategoryModule } from 'src/app/category/category.module';
import { AuthorModule } from 'src/app/author/author.module';
import { GameModule } from 'src/app/game/game.module';
import { ClientModule } from 'src/app/client/client.module';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    CategoryModule,
    BrowserAnimationsModule,
    AuthorModule,
    GameModule,
    ClientModule,
    PrestamoModule,
    MatNativeDateModule, // Asegúrate de importar MatNativeDateModule aquí
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }