import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-balance-card',
  templateUrl: './balance-card.html',
  styleUrl: './balance-card.css',
  standalone: true,
})
export class BalanceCard {
  readonly hidden = signal(false);

  toggleBalance() {
    this.hidden.update((v) => !v);
  }
}
