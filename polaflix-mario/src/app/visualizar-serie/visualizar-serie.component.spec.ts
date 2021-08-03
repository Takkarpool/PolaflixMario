import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizarSerieComponent } from './visualizar-serie.component';

describe('VisualizarSerieComponent', () => {
  let component: VisualizarSerieComponent;
  let fixture: ComponentFixture<VisualizarSerieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizarSerieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizarSerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
