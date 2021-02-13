import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistribuicaoPorMarcaComponent } from './distribuicao-por-marca.component';

describe('DistribuicaoPorMarcaComponent', () => {
  let component: DistribuicaoPorMarcaComponent;
  let fixture: ComponentFixture<DistribuicaoPorMarcaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistribuicaoPorMarcaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DistribuicaoPorMarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
