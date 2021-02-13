import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistribuicaoPorAnoComponent } from './distribuicao-por-ano.component';

describe('DistribuicaoPorAnoComponent', () => {
  let component: DistribuicaoPorAnoComponent;
  let fixture: ComponentFixture<DistribuicaoPorAnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistribuicaoPorAnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DistribuicaoPorAnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
