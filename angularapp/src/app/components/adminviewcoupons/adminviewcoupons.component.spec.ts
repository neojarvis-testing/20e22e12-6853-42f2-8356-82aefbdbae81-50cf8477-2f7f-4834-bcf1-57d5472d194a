import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewcouponsComponent } from './adminviewcoupons.component';

describe('AdminviewcouponsComponent', () => {
  let component: AdminviewcouponsComponent;
  let fixture: ComponentFixture<AdminviewcouponsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewcouponsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewcouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
