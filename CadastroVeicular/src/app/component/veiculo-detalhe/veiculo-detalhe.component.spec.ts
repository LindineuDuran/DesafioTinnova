import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeiculoDetalheComponent } from './veiculo-detalhe.component';

describe('VeiculoDetalheComponent', () => {
  let component: VeiculoDetalheComponent;
  let fixture: ComponentFixture<VeiculoDetalheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VeiculoDetalheComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VeiculoDetalheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
