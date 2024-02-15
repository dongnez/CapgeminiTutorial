import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { AuthorListComponent } from 'src/app/author/author-list/author-list.component';
import { GameListComponent } from 'src/app/game/game-list/game-list.component';
import { ClientListComponent } from 'src/app/client/client-list/client-list.component';
import { PrestamoListComponent } from 'src/app/prestamo/prestamo-list/prestamo-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/games', pathMatch: 'full' },
  { path: 'games', component: GameListComponent },
  { path: 'categories', component: CategoryListComponent },
  { path: 'authors', component: AuthorListComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'prestamos', component: PrestamoListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
