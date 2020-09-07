import {
	Component,
	OnInit
} from '@angular/core';
import {
	VeiculoService
} from 'src/app/services/veiculo.service';
import {
	ActivatedRoute,
	Router
} from '@angular/router';

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

	submitted = false;

	constructor(private veiculoService: VeiculoService,
		private route: ActivatedRoute,
		private router: Router) {}

	ngOnInit(): void {}

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
					this.submitted = true;
				},
				error => {
					console.log(error);
				});
	}

	newVeiculo(): void {
		this.chapa = '';
		this.marca = '';
		this.ano = '';
		this.descricao = '';
		this.vendido = false;

		this.submitted = false;
	}
}
