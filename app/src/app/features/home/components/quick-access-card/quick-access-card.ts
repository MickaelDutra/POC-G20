import { Component } from '@angular/core';

@Component({
  selector: 'app-quick-access-card',
  templateUrl: './quick-access-card.html',
  styleUrl: './quick-access-card.css',
  standalone: true,
})
export class QuickAccessCard {
  readonly items = [
    {
      label: 'Empréstimos',
      sublabel: 'Até R$ 50.000',
      icon: 'loan',
    },
    {
      label: 'Investimentos',
      sublabel: 'Rende 112% CDI',
      icon: 'invest',
    },
    {
      label: 'Cartão',
      sublabel: 'Limite R$ 8.000',
      icon: 'card',
    },
  ];
}
