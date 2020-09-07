import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-veiculo-detalhe',
  templateUrl: './veiculo-detalhe.component.html',
  styleUrls: ['./veiculo-detalhe.component.css']
})
export class VeiculoDetalheComponent implements OnInit {
  currentVeiculo = null;
  message = '';

  constructor(
    private veiculoService: VeiculoService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getVeiculo(this.route.snapshot.paramMap.get('id'));
  }

  getVeiculo(id): void {
    this.veiculoService.get(id)
      .subscribe(
        data => {
          this.currentVeiculo = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updateVeiculo(): void {
    this.veiculoService.update(this.currentVeiculo.id, this.currentVeiculo)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'O veiculo foi atualizado com sucesso!';
        },
        error => {
          console.log(error);
        });
  }

  updateVendido(status): void {
    const data = {
		vendido: status
    };

    this.veiculoService.sale(this.currentVeiculo.id, data)
      .subscribe(
        response => {
          this.currentVeiculo.vendido = status;
          console.log(response);
          this.message = 'O veiculo foi atualizado com sucesso!';
        },
        error => {
          console.log(error);
        });
  }

  deleteVeiculo(): void {
    this.veiculoService.delete(this.currentVeiculo.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/veiculos']);
        },
        error => {
          console.log(error);
        });
  }
}
