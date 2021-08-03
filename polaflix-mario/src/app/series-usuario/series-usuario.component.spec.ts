import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeriesUsuarioComponent } from './series-usuario.component';

describe('SeriesUsuarioComponent', () => {
  let component: SeriesUsuarioComponent;
  let fixture: ComponentFixture<SeriesUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeriesUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeriesUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
