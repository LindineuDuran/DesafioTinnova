import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VeiculosComponent } from './component/veiculos/veiculos.component';
import { VeiculoDetalheComponent } from './component/veiculo-detalhe/veiculo-detalhe.component';
import { VeiculoNovoComponent } from './component/veiculo-novo/veiculo-novo.component';
import { DistribuicaoPorAnoComponent } from './component/distribuicao-por-ano/distribuicao-por-ano.component';
import { DistribuicaoPorMarcaComponent } from './component/distribuicao-por-marca/distribuicao-por-marca.component';

@NgModule({
  declarations: [
    AppComponent,
    VeiculosComponent,
    VeiculoDetalheComponent,
    VeiculoNovoComponent,
    DistribuicaoPorAnoComponent,
    DistribuicaoPorMarcaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
