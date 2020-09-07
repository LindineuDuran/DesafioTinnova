import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-veiculo-novo',
  templateUrl: './veiculo-novo.component.html',
  styleUrls: ['./veiculo-novo.component.css']
})
export class VeiculoNovoComponent implements OnInit {
    chapa: '';
    marca: '';
    ano: '';
    descricao: '';
    vendido: false;

    // message = '';

    veiculo = {
        chapa: '',
        marca: '',
        ano: '',
        descricao: '',
        vendido: false
    };
      submitted = false;

      constructor(private veiculoService: VeiculoService) { }

      ngOnInit(): void {
      }

      saveVeiculo(): void {
        const data = {
    		chapa: this.chapa,
      		marca: this.marca,
      		ano: this.ano,
      		descricao: this.descricao,
      		vendido: this.vendido
        };

        this.veiculoService.create(data)
          .subscribe(
            response => {
              console.log(response);
              // this.message = 'O novo veiculo foi cadastrado!';
              this.submitted = true;
            },
            error => {
              console.log(error);
            });
      }

      newVeiculo(): void {
          this.veiculo = {
              chapa: '',
              marca: '',
              ano: '',
              descricao: '',
              vendido: false
          };

        this.submitted = false;
      }
}
