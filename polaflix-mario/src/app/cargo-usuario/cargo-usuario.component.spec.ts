import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargoUsuarioComponent } from './cargo-usuario.component';

describe('CargoUsuarioComponent', () => {
  let component: CargoUsuarioComponent;
  let fixture: ComponentFixture<CargoUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CargoUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CargoUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
