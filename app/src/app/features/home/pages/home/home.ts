import { Component } from '@angular/core';
import { BalanceCard } from '../../components/balance-card/balance-card';
import { ActionsGrid } from '../../components/actions-grid/actions-grid';
import { QuickAccessCard } from '../../components/quick-access-card/quick-access-card';
import { AiFloatingMenu } from '../../components/ai-floating-menu/ai-floating-menu';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrl: './home.css',
  standalone: true,
  imports: [BalanceCard, ActionsGrid, QuickAccessCard, AiFloatingMenu],
})
export class Home {
  readonly navItems = [
    { icon: 'home', label: 'Início', active: true },
    { icon: 'activity', label: 'Atividade', active: false },
    { icon: 'pix', label: 'Pix', active: false },
    { icon: 'benefits', label: 'Benefícios', active: false },
    { icon: 'menu', label: 'Menu', active: false },
  ];

  readonly highlights = [
    {
      title: 'Seu dinheiro rendendo',
      description: 'Invista a partir de R$ 1 e veja seu saldo crescer todo dia.',
      tag: 'Novidade',
      variant: 'primary',
    },
    {
      title: 'Empréstimo pré-aprovado',
      description: 'Até R$ 50.000 com as melhores taxas do mercado.',
      tag: 'Oferta',
      variant: 'dark-red',
    },
    {
      title: 'Cashback em tudo',
      description: 'Ganhe de volta em cada compra no seu cartão.',
      tag: 'Exclusivo',
      variant: 'dark',
    },
  ];
}
