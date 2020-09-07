import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-distribuicao-por-ano',
  templateUrl: './distribuicao-por-ano.component.html',
  styleUrls: ['./distribuicao-por-ano.component.css']
})
export class DistribuicaoPorAnoComponent implements OnInit {
  lstQtdsAno: any;
  constructor(private veiculoService: VeiculoService) { }

  ngOnInit(): void {
    this.retrieveDistribAno();
  }

  retrieveDistribAno(): void {
    this.veiculoService.distribuicaoAno()
      .subscribe(
        data => {
          this.lstQtdsAno = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
