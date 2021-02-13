import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {VeiculosComponent} from './component/veiculos/veiculos.component';
import {VeiculoDetalheComponent} from './component/veiculo-detalhe/veiculo-detalhe.component';
import {VeiculoNovoComponent} from './component/veiculo-novo/veiculo-novo.component';
import {DistribuicaoPorAnoComponent} from './component/distribuicao-por-ano/distribuicao-por-ano.component';
import {DistribuicaoPorMarcaComponent} from './component/distribuicao-por-marca/distribuicao-por-marca.component';

const routes: Routes = [
  { path: '', redirectTo: 'veiculos', pathMatch: 'full' },
  { path: 'veiculos', component: VeiculosComponent },
  { path: 'veiculos/:id', component: VeiculoDetalheComponent },
  { path: 'veiculo-novo', component: VeiculoNovoComponent },
  { path: 'veiculo-detalhe', component: VeiculoDetalheComponent },
  { path: 'distribuicao-por-ano', component: DistribuicaoPorAnoComponent },
  { path: 'distribuicao-por-marca', component: DistribuicaoPorMarcaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
