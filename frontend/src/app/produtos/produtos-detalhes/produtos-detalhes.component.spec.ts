import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutosDetalhesComponent } from './produtos-detalhes.component';

describe('ProdutosDetalhesComponent', () => {
  let component: ProdutosDetalhesComponent;
  let fixture: ComponentFixture<ProdutosDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProdutosDetalhesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProdutosDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
