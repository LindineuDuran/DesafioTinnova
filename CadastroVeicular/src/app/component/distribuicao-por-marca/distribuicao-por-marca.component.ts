import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-distribuicao-por-marca',
  templateUrl: './distribuicao-por-marca.component.html',
  styleUrls: ['./distribuicao-por-marca.component.css']
})
export class DistribuicaoPorMarcaComponent implements OnInit {
    lstQtdsMarca: any;
    constructor(private veiculoService: VeiculoService) { }

    ngOnInit(): void {
      this.retrieveDistribMarca();
    }

    retrieveDistribMarca(): void {
      this.veiculoService.distribuicaoMarca()
        .subscribe(
          data => {
            this.lstQtdsMarca = data;
            console.log(data);
          },
          error => {
            console.log(error);
          });
    }
}
