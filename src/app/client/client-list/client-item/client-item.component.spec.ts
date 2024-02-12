import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientItemComponent } from './client-item.component';

describe('ClientItemComponent', () => {
  let component: ClientItemComponent;
  let fixture: ComponentFixture<ClientItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientItemComponent]
    });
    fixture = TestBed.createComponent(ClientItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});